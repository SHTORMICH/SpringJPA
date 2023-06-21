package com.kabaldin.todo;

import com.kabaldin.todo.controller.TodoItemController;
import com.kabaldin.todo.entity.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TodoItemControllerTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemController todoItemController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoItemController).build();
    }

    @Test
    void getAllTodoItems_ShouldReturnListOfTodoItems() throws Exception {
        List<TodoItem> todoItems = new ArrayList<>();
        TodoItem todoItem1 = new TodoItem();
        TodoItem todoItem2 = new TodoItem();

        todoItem1.setId(1L);
        todoItem1.setTitle("Title 1");
        todoItem1.setDescription("Description 1");

        todoItem2.setId(2L);
        todoItem2.setTitle("Title 2");
        todoItem2.setDescription("Description 2");

        todoItems.add(todoItem1);
        todoItems.add(todoItem2);

        when(todoItemRepository.findAll()).thenReturn(todoItems);

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Title 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"));

        verify(todoItemRepository, times(1)).findAll();
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void createTodoItem_ShouldReturnCreatedTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem();

        todoItem.setId(1L);
        todoItem.setTitle("New Title");
        todoItem.setDescription("New Description");

        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(todoItem);

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Title\",\"description\":\"New Description\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.description").value("New Description"));

        verify(todoItemRepository, times(1)).save(any(TodoItem.class));
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void getTodoItemById_ExistingId_ShouldReturnTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("Title 1");
        todoItem.setDescription("Description 1");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));

        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));

        verify(todoItemRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void getTodoItemById_NonExistingId_ShouldReturnNotFound() throws Exception {
        when(todoItemRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());

        verify(todoItemRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void updateTodoItem_ExistingId_ShouldReturnUpdatedTodoItem() throws Exception {
        TodoItem existingTodoItem = new TodoItem();
        TodoItem updatedTodoItem = new TodoItem();

        existingTodoItem.setId(1L);
        existingTodoItem.setTitle("Title 1");
        existingTodoItem.setDescription("Description 1");

        updatedTodoItem.setId(1L);
        updatedTodoItem.setTitle("Updated Title"); // Update the title
        updatedTodoItem.setDescription("Updated Description"); // Update the description

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(existingTodoItem));
        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(updatedTodoItem);

        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(todoItemRepository, times(1)).findById(1L);
        verify(todoItemRepository, times(1)).save(any(TodoItem.class));
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void updateTodoItem_NonExistingId_ShouldReturnNotFound() throws Exception {
        when(todoItemRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());

        verify(todoItemRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(todoItemRepository);
    }

    @Test
    void deleteTodoItem_ShouldDeleteItemAndReturnNoContent() throws Exception {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("Title 1");
        todoItem.setDescription("Description 1");

        doNothing().when(todoItemRepository).deleteById(1L);

        mockMvc.perform(delete("/api/todo/delete/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(todoItemRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(todoItemRepository);
    }

}