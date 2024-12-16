package com.company.catalogue.backend.controller;

import com.company.catalogue.backend.model.Tag;
import com.company.catalogue.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/tag")
@RestController
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){ this.tagService = tagService;}

    // Get a tag by its id provided as a query param e.g. <url-here>?id=1
    @GetMapping("")
    public Tag getTag(@RequestParam int id){
        Optional<Tag> tag = tagService.getTag(id);
        return (Tag) tag.orElse(null);
    }

    // Get All tag from Database. <url-here>/all
    @GetMapping("/all")
    public List<Tag> getAllTags(){
       return tagService.getAll();
    }

    // Add new tag to the database. Data is passed in through raw json.
    @PostMapping("/add")
    public void addTag(@RequestBody Tag newTag){
        tagService.addTag(newTag);
    }

    // Update a tag by its id
    @PutMapping("/update")
    public void updateTag(@RequestBody Tag newTag, int id){
        tagService.updateTag(newTag, id);
    }

    // Delete a tag from database by its id
    @DeleteMapping("/delete")
    public void deleteTag(@RequestParam int id){
        tagService.deleteTag(id);
    }
}
