package br.com.uds.tasklist;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.uds.tasklist.TasklistApplication;
import br.com.uds.tasklist.model.Task;
import br.com.uds.tasklist.repository.TaskRepository;
import br.com.uds.tasklist.rest.TaskController;
import br.com.uds.tasklist.service.TaskService;
import br.com.uds.tasklist.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TasklistApplication.class})
public class TaskControllerIntTest {

	private static final String DEFAULT_DESCRIPTION = "Flutter";
	private static final String UPDATED_DESCRIPTION = "React Native";

	private static final Boolean DEFAULT_STATUS = false;
	private static final Boolean UPDATED_STATUS = true;
	
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@Autowired
	@Qualifier("mappingJackson2HttpMessageConverter")
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	private MockMvc restTaskMockMvc;

	private Task task;

	@Before
	public void setup() {
		final TaskController taskController = new TaskController(taskService);
		this.restTaskMockMvc = MockMvcBuilders.standaloneSetup(taskController)
				.setMessageConverters(jacksonMessageConverter).build();
	}

	private static Task createEntity() {
		Task task = new Task();
		task.setDescription(DEFAULT_DESCRIPTION);
		task.setStatus(DEFAULT_STATUS);
		return task;
	}

	@Before
	public void initTest() {
		task = createEntity();
	}

	@Test
	@Transactional
	public void criarTask() throws Exception {
		long quantityRecordsBeforeSave = taskRepository.count();

		restTaskMockMvc.perform(post("/api/tasks")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(task)))
			.andExpect(status().isCreated());

		long quantityRecordsAfterCreated = taskRepository.count();

		assertThat(quantityRecordsAfterCreated).isEqualTo(quantityRecordsBeforeSave + 1);
		assertThat(task.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
		assertThat(task.getStatus()).isEqualTo(DEFAULT_STATUS);
	}

	@Test
	public void buscarTasks() throws Exception {
		taskService.save(task);

		restTaskMockMvc.perform(get("/api/tasks"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.[*].id").value(hasItem(task.getId().intValue())))
			.andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
			.andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
		
	}

	@Test
	public void buscarTask() throws Exception {
		task = taskService.save(task);

		restTaskMockMvc.perform(get("/api/tasks/{id}", task.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").value(task.getId().intValue()))
			.andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
			.andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
	}

	@Test
	public void atualizarTask() throws IOException, Exception {
		task = taskService.save(task);
		task.setDescription(UPDATED_DESCRIPTION);
		task.setStatus(UPDATED_STATUS);

		restTaskMockMvc.perform(put("/api/tasks")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(task)))
			.andExpect(status().isNoContent());

		Optional<Task> updatedTask = taskService.findById(task.getId());
		assertThat(updatedTask.get().getDescription()).isEqualTo(UPDATED_DESCRIPTION);
		assertThat(updatedTask.get().getStatus()).isEqualTo(UPDATED_STATUS);
	}

	@Test
	public void deletarTask() throws Exception {
		task = taskService.save(task);

		long quantityRecordsBeforeSave = taskRepository.count();

		restTaskMockMvc.perform(delete("/api/tasks/{id}", task.getId()))
			.andExpect(status().isNoContent());

		long quantityRecordsAfterCreated = taskRepository.count();

		assertThat(quantityRecordsAfterCreated).isEqualTo(quantityRecordsBeforeSave - 1);
	}
}