package com.evan.lejo.controller.admin;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Category;
import com.evan.lejo.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "AdminCategoriesController" )
@RequestMapping( "/lejo/admin" )
public class CategoryController {

    protected final Create< Category > createCategory;
    protected final Update< Category > updateCategoryName;
    protected final CategoryRepository categoryRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public CategoryController(
            Create< Category > createCategory,
            Update< Category > updateCategoryName,
            CategoryRepository categoryRepository,
            DataStorageHandler dataStorageHandler,
            Request request
    ) {
        this.createCategory     = createCategory;
        this.updateCategoryName = updateCategoryName;
        this.categoryRepository = categoryRepository;
        this.dataStorageHandler = dataStorageHandler;
        this.request            = request;
    }


    @Transactional
    @GetMapping( "/categories/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getCategory( @PathVariable( "id" ) long id ) {
        Category category = categoryRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( category, GroupType.ADMIN ) );
    }


    @Transactional
    @PostMapping( "/categories" )
    public ResponseEntity< Map< String, Object > > create() {
        Category category = new Category();

        createCategory.create( request, category );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( category, GroupType.ADMIN ) );
    }


    @Transactional
    @PatchMapping( "/categories/{id:[0-9]+}/name" )
    public ResponseEntity< Map< String, Object > > updateName( @PathVariable( "id" ) long id ) {
        Category category = categoryRepository.findOrFail( id );

        updateCategoryName.update( request, category );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
