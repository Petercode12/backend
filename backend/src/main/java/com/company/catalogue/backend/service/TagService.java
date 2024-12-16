package com.company.catalogue.backend.service;

import com.company.catalogue.backend.model.Tag;
import com.company.catalogue.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService( TagRepository tagRepository){ this.tagRepository = tagRepository;};

    public Optional<Tag> getTag(long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> getAll(){
        return tagRepository.findAll();
    }

    public Tag addTag( Tag tag){
        return tagRepository.save(tag);
    }

    public void deleteTag(long id){
        tagRepository.deleteById(id);
    }

    public Tag updateTag(Tag newTag, long id){
        Optional<Tag> optionalTag = tagRepository.findById(id);

        if(optionalTag.isPresent()){
            Tag tag = optionalTag.get();

            // Update the fields that should be updated
            tag.setName(newTag.getName());

            // Save new tag to database
            return tagRepository.save(tag);
        }
        else {
            return null;
        }
    }
}