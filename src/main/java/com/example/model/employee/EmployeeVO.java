package com.example.model.employee;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeVO {
 
	private @NotNull int employeId;
    private @NonNull String employeName;
    private @NonNull String age;
    private @NonNull String address;
 
}
