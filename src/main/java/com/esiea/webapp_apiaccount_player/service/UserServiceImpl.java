package com.esiea.webapp_apiaccount_player.service;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

import com.esiea.webapp_apiaccount_player.model.User;
import com.esiea.webapp_apiaccount_player.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private String filePath;


	@Override
	public void save(User user) {
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		userRepository.save(user);
		saveDataUser(user,password);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void loadDataUsers(){

		String DataUsers;
		this.filePath = new File("quiz_v7/src/main/resources/data/userData").getAbsolutePath();
		try
		{

			FileInputStream fis=new FileInputStream(this.filePath);

			Scanner sc= new Scanner(fis);    //file to be scanned

			while(sc.hasNextLine())
			{
				DataUsers = sc.nextLine();
				//System.out.println(DataUsers);
				if(DataUsers.charAt(0)!='#'){
					String[] listTemp=DataUsers.split(";");
					User user = new User();
					//System.out.println(listTemp[1]);
					user.setId(Integer.parseInt(listTemp[0]));
					user.setUsername(listTemp[1]);
					user.setPassword(listTemp[2]);
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					user.setRole(listTemp[3]);
					user.setPoints(Integer.parseInt(listTemp[4]));

					userRepository.save(user);
				}
			}
			sc.close();     //closes the scanner
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveDataUser(User user,String password){
		this.filePath = new File("src/main/resources/data/userData").getAbsolutePath();
		String DataUser  = Integer.toString(user.getId())
				+ ';' + user.getUsername()
				+ ';' + password
				+ ';' + user.getRole()
				+ ';' + Integer.toString(user.getPoints());

		try(FileWriter fw = new FileWriter(this.filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
		{

			out.println(DataUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
