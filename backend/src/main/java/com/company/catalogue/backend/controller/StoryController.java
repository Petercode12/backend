package com.company.catalogue.backend.controller;

import com.company.catalogue.backend.dto.StoryDTO;
import com.company.catalogue.backend.model.Story;
import com.company.catalogue.backend.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/story")
@RestController
public class StoryController {

	private final StoryService storyService;

	@Autowired
	public StoryController(StoryService storyService) {
		this.storyService = storyService;
	}

	// Get story by id provided as a query param e.g. <url-here>?id=3
	@GetMapping("")
	public Story getStory(@RequestParam int id) {
		Optional<Story> st = storyService.getStory(id);
		return (Story) st.orElse(null);
	}

	// Get all stories from the database
	@GetMapping("/all")
	public List<Story> getAllStories() {
		return storyService.getAll();
	}

	// Add new story to the database. Data is passed in through raw json.
	@PostMapping("/add")
	public Story addStory(@RequestBody StoryDTO new_story) {
		return storyService.addStory(new_story);
	}

	// Update story by id provided as a query param e.g. <url-here>?id=3
	@PutMapping("/update")
	public void updateStory(@RequestBody StoryDTO new_story, @RequestParam int id) {
		storyService.updateStory(new_story, id);
	}

	// Delete story by id provided as a query param e.g. <url-here>?id=3
	@DeleteMapping("/delete")
	public void deleteStory(@RequestParam int id) {
		storyService.deleteStory(id);
	}

}