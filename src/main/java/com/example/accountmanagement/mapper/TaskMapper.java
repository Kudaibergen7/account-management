package com.example.accountmanagement.mapper;

import com.example.accountmanagement.dto.TaskDTO;
import com.example.accountmanagement.entity.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskDTO convertToDto(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }
    public Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}



