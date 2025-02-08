package com.__app.Service;

import java.util.Map;

import com.__app.Dto.QuoteResponseDTO;
import com.__app.Dto.ResetPwdDTO;
import com.__app.Dto.UserDTO;

public interface UserService {
	public UserDTO login(String email, String pwd);

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public boolean isEmailUnique(String email);

	public boolean register(UserDTO userDto);

	public boolean resetPwd(ResetPwdDTO resetPwdDto);

	public QuoteResponseDTO getQuotation();
}
