package com.tinybit.service.core;

import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Counter;
import com.tinybit.model.CryptoDetail;
import com.tinybit.model.Detail;
import com.tinybit.service.crypto.CryptoService;
import com.tinybit.service.crypto.CryptoServiceImpl;
import com.tinybit.service.db.DbService;
import com.tinybit.service.db.DbServiceImpl;
import com.tinybit.service.workflow.WorkflowService;
import com.tinybit.service.workflow.WorkflowServiceImpl;
import redis.clients.jedis.Jedis;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tinybit")
public class CoreResource {

    // pre prequisites
    private Jedis jedis = new Jedis();
    private DbService dbService = new DbServiceImpl();
    private CryptoService cryptoService = new CryptoServiceImpl();

    @Path("get_count")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() {

        Long currentCount = dbService.getCount();
        Counter counter = null;
        counter = new Counter(currentCount);
        return Response.ok(counter).status(Response.Status.OK).build();
    }

    @Path("add_count")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementCounter() {

        Long currentCount = dbService.increment();
        Counter counter = null;
        counter = new Counter(currentCount);
        return Response.ok(counter).status(Response.Status.OK).build();
    }

    @Path("cc")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCCDetails() {
        List<CryptoDetail> cryptoDetails = cryptoService.get();

        if(cryptoDetails == null || cryptoDetails.isEmpty())
            cryptoService.load();

        cryptoDetails = cryptoService.get();

        return Response.ok(cryptoDetails).status(Response.Status.OK).build();
    }
}
