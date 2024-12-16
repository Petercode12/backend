package com.company.catalogue.backend.service;

import com.company.catalogue.backend.dto.StoryDTO;
import com.company.catalogue.backend.model.Company;
import com.company.catalogue.backend.model.Story;
import com.company.catalogue.backend.model.User;
import com.company.catalogue.backend.repository.CompanyRepository;
import com.company.catalogue.backend.repository.StoryRepository;
import com.company.catalogue.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
	private final StoryRepository storyRepository;
	private final UserRepository userRepository;
	private final CompanyRepository companyRepository;

	@Autowired
	public StoryService(StoryRepository repo, UserRepository userRepository, CompanyRepository companyRepository) {
		this.storyRepository = repo;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
	}

	public Optional<Story> getStory(long id) {
		return storyRepository.findById(id);
	}

	public List<Story> getAll() {
		return storyRepository.findAll();
	}

	public Story addStory(Story st) {
		st.setPosted(new Date());
		return storyRepository.save(st);
	}

	public Story addStory(StoryDTO st) {
		Optional<Company> companyOptional = companyRepository.findById(st.getCompany_id());
		Optional<User> userOptional = userRepository.findById(st.getUser_id());
		Company company = companyOptional.orElseThrow();
		User user = userOptional.orElseThrow();

		Story story = new Story();
		story.setCompany(company);
		story.setUser(user);
		story.setTitle(st.getTitle());
		story.setContent(st.getContent());
		story.setLikes(st.getLikes());
		story.setPosted(new Date());
		story.setStatus(st.getStatus());
		return storyRepository.save(story);
	}


	public void deleteStory(long id) {
		storyRepository.deleteById(id);
	}

	public Story updateStory(StoryDTO st, long id) {
		Optional<Story> optionalStory = storyRepository.findById(id);

		Optional<Company> companyOptional = companyRepository.findById(st.getCompany_id());
		Optional<User> userOptional = userRepository.findById(st.getUser_id());

		Company company = companyOptional.orElseThrow();
		User user = userOptional.orElseThrow();

		if (optionalStory.isPresent()) {
			Story story = optionalStory.get();

			story.setUser(user);
			story.setCompany(company);
			story.setTitle(st.getTitle());
			story.setContent(st.getContent());
			story.setLikes(st.getLikes());
			story.setStatus(st.getStatus());

			return storyRepository.save(story);
		} else {
			return null;
		}
	}


}