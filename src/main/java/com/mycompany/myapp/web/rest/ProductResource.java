package com.mycompany.myapp.web.rest;

import static jakarta.ws.rs.core.UriBuilder.fromPath;

import com.mycompany.myapp.service.Paged;
import com.mycompany.myapp.service.ProductService;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.vm.PageRequestVM;
import com.mycompany.myapp.web.rest.vm.SortRequestVM;
import com.mycompany.myapp.web.util.HeaderUtil;
import com.mycompany.myapp.web.util.PaginationUtil;
import com.mycompany.myapp.web.util.ResponseUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.Optional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Product}.
 */
@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    @ConfigProperty(name = "application.name")
    String applicationName;

    @Inject
    ProductService productService;

    /**
     * {@code POST  /products} : Create a new product.
     *
     * @param productDTO the productDTO to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new productDTO, or with status {@code 400 (Bad Request)} if the product has already an ID.
     */
    @POST
    public Response createProduct(@Valid ProductDTO productDTO, @Context UriInfo uriInfo) {
        log.debug("REST request to save Product : {}", productDTO);
        if (productDTO.id != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = productService.persistOrUpdate(productDTO);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /products} : Updates an existing product.
     *
     * @param productDTO the productDTO to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated productDTO,
     * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productDTO couldn't be updated.
     */
    @PUT
    @Path("/{id}")
    public Response updateProduct(@Valid ProductDTO productDTO, @PathParam("id") Long id) {
        log.debug("REST request to update Product : {}", productDTO);
        if (productDTO.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = productService.persistOrUpdate(productDTO);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, productDTO.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     *
     * @param id the id of the productDTO to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /products} : get all the products.
     *
     * @param pageRequest the pagination information.
     * @return the {@link Response} with status {@code 200 (OK)} and the list of products in body.
     */
    @GET
    public Response getAllProducts(@BeanParam PageRequestVM pageRequest, @BeanParam SortRequestVM sortRequest, @Context UriInfo uriInfo) {
        log.debug("REST request to get a page of Products");
        var page = pageRequest.toPage();
        var sort = sortRequest.toSort();
        Paged<ProductDTO> result = productService.findAll(page);
        var response = Response.ok().entity(result.content);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);
        return response.build();
    }

    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the productDTO, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") Long id) {
        log.debug("REST request to get Product : {}", id);
        Optional<ProductDTO> productDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDTO);
    }
}
