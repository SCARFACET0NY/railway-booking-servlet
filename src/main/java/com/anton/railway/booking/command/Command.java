package com.anton.railway.booking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface for servlet command
 */
public interface Command {
    /**
     * Abstract method for handling incoming requests
     * @param req  - incoming request for processing
     * @param resp - response to the caller
     * @return array of strings with page of path and forward or redirect action
     * @throws IOException
     */
    String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}