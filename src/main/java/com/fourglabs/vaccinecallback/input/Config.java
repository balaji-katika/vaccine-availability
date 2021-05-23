package com.fourglabs.vaccinecallback.input;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Config {
	int districtId;
	List<String> phoneNumbers;
}
