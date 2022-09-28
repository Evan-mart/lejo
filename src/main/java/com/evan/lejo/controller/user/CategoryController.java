package com.evan.lejo.controller.user;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Delete;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Category;
import com.evan.lejo.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "UserCategoriesController" )
@RequestMapping( "/lejo/users" )
public class CategoryController {

    protected final Create< Category > createCategory;
    protected final Update< Category > updateCategoryName;
    protected final Delete< Category > deleteCategory;
    protected final CategoryRepository categoryRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public CategoryController(
            Create< Category > createCategory,
            Update< Category > updateCategoryName,
            Delete< Category > deleteCategory,
            CategoryRepository categoryRepository,
            DataStorageHandler dataStorageHandler,
            Request request
    ) {
        this.createCategory     = createCategory;
        this.updateCategoryName = updateCategoryName;
        this.deleteCategory     = deleteCategory;
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
    @GetMapping( "/categories" )
    public ResponseEntity< List< Map< String, Object > > > getAllCategories() {
        List< Category > categories = categoryRepository.findAll();

        return ResponseEntity.ok( Encoder.encode( categories, GroupType.USER ) );
    }
}
