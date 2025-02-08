package com.__app.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.__app.Dto.QuoteResponseDTO;
import com.__app.Dto.ResetPwdDTO;
import com.__app.Dto.UserDTO;
import com.__app.Entity.CitiesMaster;
import com.__app.Entity.CountryMaster;
import com.__app.Entity.StatesMaster;
import com.__app.Entity.UserMaster;
import com.__app.Repo.CityRepo;
import com.__app.Repo.CountryRepo;
import com.__app.Repo.StateRepo;
import com.__app.Repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailService emailService;

	@Override
	public UserDTO login(String email, String pwd) {
		
		UserMaster user=userRepo.findByEmailAndPassword(email,pwd);
		if(user != null) {
			UserDTO userDto=new UserDTO();
			userDto.setName(user.getUsername());
			userDto.setEmail(user.getEmail());
			userDto.setPhno(user.getPhone());
			userDto.setPwd(user.getPassword());
			userDto.setPwdUpdated(user.getPasswordUpdated());
			userDto.setCountryId(user.getCountry().getCountryId());
			userDto.setStateId(user.getState().getStateId());
			userDto.setCityId(user.getCity().getCityId());
			
			return userDto;
		}
		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMaster> countriesList=countryRepo.findAll();
		Map<Integer,String> countriesMap = new HashMap<>();
		
		countriesList.stream().forEach(n->{
			countriesMap.put(n.getCountryId(), n.getCountryName());
			});
		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StatesMaster> stateList= stateRepo.findByCountryCountryId(countryId);
		Map<Integer,String> stateMap=new HashMap<>();
		stateList.stream().forEach(state->{
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CitiesMaster> cityList=cityRepo.findByStateStateId(stateId);
		Map<Integer,String> cityMap=new HashMap<>();
		cityList.stream().forEach(city->{
			cityMap.put(city.getCityId(), city.getCityName());
		});
		return cityMap;
	}

	@Override
	public boolean isEmailUnique(String email) {
		
		return null==userRepo.findByEmail(email);
	}

	@Override
	public boolean register(UserDTO userDto) {
		String randomPwd=getRandomPwd();
		userDto.setPwd(randomPwd);
		userDto.setPwdUpdated("NO");
		UserMaster user=new UserMaster();
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPwd());
		user.setPasswordUpdated(userDto.getPwdUpdated());
		user.setPhone(userDto.getPhno());
		user.setUsername(userDto.getName());

		BeanUtils.copyProperties(user, userDto);
		
		Optional<CountryMaster> country= countryRepo.findById(userDto.getCountryId());
		user.setCountry(country.get());
		
		Optional<StatesMaster> state=stateRepo.findById(userDto.getStateId());
		user.setState(state.get());
		
		Optional<CitiesMaster> city=cityRepo.findById(userDto.getCityId());
		user.setCity(city.get());
		
		UserMaster savedUser=userRepo.save(user);
		
		if(savedUser != null) {
			String subject = " your Registration is Successfull... ";
			String body= "your Account Login Password is  "+savedUser.getPassword();
			return emailService.sendEmail(userDto.getEmail(),subject,body);
		}
		return false;
	}

	private String getRandomPwd() {
		String saltChars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890#@!$&*@";
		StringBuilder pwd= new StringBuilder();
		
		Random rndm = new Random();
		
		while(pwd.length()<7) {
			int index=(int) (rndm.nextFloat()*saltChars.length());
			pwd.append(saltChars.charAt(index));
		}
		return pwd.toString();
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDto) {
		UserMaster userEntity = userRepo.findByEmail(resetPwdDto.getEmail());
		userEntity.setPassword(resetPwdDto.getNewPwd());
		userEntity.setPasswordUpdated("YES");
		
		UserMaster saved= userRepo.save(userEntity);
		return saved != null;
	}

	@Override
	public QuoteResponseDTO getQuotation() {
		String apiUrl="https://dummyjson.com/quotes/random";
		RestTemplate rsTl= new RestTemplate();
		ResponseEntity<QuoteResponseDTO> quote= rsTl.getForEntity(apiUrl, QuoteResponseDTO.class);
		return quote.getBody();
	}

}
