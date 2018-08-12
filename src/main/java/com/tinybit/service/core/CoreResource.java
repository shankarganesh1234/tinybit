package com.tinybit.service.core;

import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Detail;
import com.tinybit.service.db.DbService;
import com.tinybit.service.db.DbServiceImpl;
import com.tinybit.service.workflow.WorkflowService;
import com.tinybit.service.workflow.WorkflowServiceImpl;
import redis.clients.jedis.Jedis;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tinybit")
public class CoreResource {

    // pre prequisites
    private Jedis jedis = new Jedis();
    private DbService dbService = new DbServiceImpl(jedis);
    private WorkflowService workflowService = new WorkflowServiceImpl(dbService);

    @Path("{key}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecord(@PathParam("key") String key) throws TinyBitException {

        Detail detail = workflowService.getRecord(key);
        detail.setKey(key);
        return Response.ok(detail).status(Response.Status.OK).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRecord(Detail detail) throws Exception {

        String key = workflowService.createRecord(detail);
        detail.setKey(key);
        return Response.ok(detail).status(Response.Status.OK).build();
    }
}
