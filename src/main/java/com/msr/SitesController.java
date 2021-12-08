package com.msr;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import com.msr.model.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Respond to site requests
 */
@RestController
@RequestMapping("/sites")
public class SitesController {

    /* Sample Output messages. */
    private final static String SAMPLE_RESPONSE_BASE = "This is a sample response to test if SitesController is responding appropriately. ";
    static final String SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "The request param you passed was: ";
    static final String NO_SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "No request param was provided.";
    static final String SAMPLE_EXCEPTION_MESSAGE = SAMPLE_RESPONSE_BASE + "An expected error was thrown.";

    @Autowired
    JdbcTemplate jdbcTemplate;



    @ApiOperation("Returns a specific site by id")
    @GetMapping("/{id}")
    public Site getSiteByIdResponse(@PathVariable("id") String id) {
        jdbcTemplate.execute("DROP TABLE IF EXISTS sites");
        jdbcTemplate.execute("CREATE TABLE sites (id INT, name NVARCHAR, address NVARCHAR, city NVARCHAR, state NVARCHAR, zipcode NVARCHAR)");
        jdbcTemplate.execute("INSERT INTO sites (id, name, address, city, state, zipcode) VALUES (1, 'Site1', 'Address1', 'City1', 'State1', '12345')");
        String sql = "SELECT * FROM sites WHERE id = " + id;
        Site site = jdbcTemplate.queryForObject(sql,
            BeanPropertyRowMapper.newInstance(Site.class));
        return site;
    }

    @ApiOperation("Returns a list of all sites")
    @GetMapping("/")
    public List<Site> getSitesResponse() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS sites");
        jdbcTemplate.execute("CREATE TABLE sites (id INT, name NVARCHAR, address NVARCHAR, city NVARCHAR, state NVARCHAR, zipcode NVARCHAR)");
        jdbcTemplate.execute("INSERT INTO sites (id, name, address, city, state, zipcode) VALUES (1, 'Site1', 'Address1', 'City1', 'State1', '12345')");
        String sql = "SELECT * FROM sites";
        List<Site> listSites = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Site.class));

        return listSites;
    }

    /**
     * Used simply to check if this controller is responding to requests.
     * Has no function other than echoing.
     *
     * @return A sample message based on the input parameters.
     * @throws RuntimeException Only when 'throwError' is true.
     */
    @ApiOperation("Returns a sample message for baseline controller testing.")
    @GetMapping("/sample")
    public String getSampleResponse(@ApiParam("The message that will be echoed back to the user.")
                                    @RequestParam(required = false) final String message,
                                    @ApiParam("Forces this endpoint to throw a generic error.")
                                    @RequestParam(required = false) final boolean throwError) {
        String response;
        if (throwError) {
            throw new RuntimeException(SAMPLE_EXCEPTION_MESSAGE);
        } else if (!StringUtils.hasLength(message)) {
            response = NO_SAMPLE_PARAM_PROVIDED;
        } else {
            response = SAMPLE_PARAM_PROVIDED + message;
        }
        return response;
    }
}