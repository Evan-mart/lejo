package com.evan.lejo.controller;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.model.Dish;
import com.evan.lejo.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
/*@RestController
@RequestMapping( "/dish" )*/
public class DishController {

    protected final Create< Dish >     createDish;
    protected final Update< Dish >     updateTitle;
    protected final Update< Dish >     updateDescription;
    protected final Update< Dish >     updatePrice;
    protected final DishRepository     dishRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public DishController(
            Create< Dish > createDish,
            Update< Dish > updateTitle,
            Update< Dish > updateDescription,
            Update< Dish > updatePrice,
            DishRepository dishRepository,
            DataStorageHandler dataStorageHandler,
            Request request ) {
        this.createDish         = createDish;
        this.updateTitle        = updateTitle;
        this.updateDescription  = updateDescription;
        this.updatePrice        = updatePrice;
        this.dishRepository     = dishRepository;
        this.dataStorageHandler = dataStorageHandler;
        this.request            = request;
    }


    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getDish( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( dish ) );
    }


    @Transactional
    @PostMapping
    public ResponseEntity< Map< String, Object > > create() {
        Dish dish = new Dish();

        createDish.create( request, dish );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( dish ) );
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/title" )
    public ResponseEntity< Map< String, Object > > updateTitle( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updateTitle.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/description" )
    public ResponseEntity< Map< String, Object > > updateDescription( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updateDescription.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/price" )
    public ResponseEntity< Map< String, Object > > updatePrice( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        updatePrice.update( request, dish );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
