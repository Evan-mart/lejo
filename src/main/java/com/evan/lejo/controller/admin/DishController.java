package com.evan.lejo.controller.admin;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Category;
import com.evan.lejo.entity.Dish;
import com.evan.lejo.repository.CategoryRepository;
import com.evan.lejo.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "AdminDishesController" )
@RequestMapping( "/lejo/admin" )
public class DishController {

    protected final Create< Dish >     createDish;
    protected final Update< Dish >     updateDishTitle;
    protected final Update< Dish >     updateDishDescription;
    protected final Update< Dish >     updateDishPrice;
    protected final DishRepository     dishRepository;
    protected final CategoryRepository categoryRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public DishController(
            Create< Dish > createDish,
            Update< Dish > updateDishTitle,
            Update< Dish > updateDishDescription,
            Update< Dish > updateDishPrice,
            DishRepository dishRepository,
            CategoryRepository categoryRepository,
            DataStorageHandler dataStorageHandler,
            Request request
    ) {
        this.createDish            = createDish;
        this.updateDishTitle       = updateDishTitle;
        this.updateDishDescription = updateDishDescription;
        this.updateDishPrice       = updateDishPrice;
        this.dishRepository        = dishRepository;
        this.categoryRepository    = categoryRepository;
        this.dataStorageHandler    = dataStorageHandler;
        this.request               = request;
    }


    @Transactional
    @GetMapping( "/dishes/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getDish( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( dish, GroupType.ADMIN ) );
    }


    @Transactional
    @PostMapping( "/categories/{id:[0-9]+}/dish" )
    public ResponseEntity< Map< String, Object > > create( @PathVariable( "id" ) long id ) {
        Category category = categoryRepository.findOrFail( id );

        Dish dish = new Dish();

        dish.setCategory( category );

        createDish.create( request, dish );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( dish, GroupType.ADMIN ) );
    }


    @Transactional
    @PatchMapping( "/dishes/{id:[0-9]+}/title" )
    public ResponseEntity< Map< String, Object > > updateTitle( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updateDishTitle.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/dishes/{id:[0-9]+}/description" )
    public ResponseEntity< Map< String, Object > > updateDescription( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updateDishDescription.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/dishes/{id:[0-9]+}/price" )
    public ResponseEntity< Map< String, Object > > updatePrice( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updateDishPrice.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
