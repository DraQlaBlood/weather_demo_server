package com.weather.demo.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.weather.demo.dao.*;
import com.weather.demo.exceptions.*;
import com.weather.demo.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api(value = "CRUD operations API")
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping("/weather/v1")
public class WeatherController {

    @Autowired
    AgencyDAO agencyDAO;

    @Autowired
    MetricDAO metricDAO;

    @Autowired
    RegionDAO regionDAO;

    @Autowired
    StationDAO stationDAO;


    /* GET Requests to all documents*/

    //Get all agencies
    @ApiOperation(value = "Get all agencies")
    @GetMapping(value="/agency")
    public MappingJacksonValue  agencyList(){
        List<Agency> allAgencies = agencyDAO.findAll();
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");

        FilterProvider listOfFilters = new SimpleFilterProvider().addFilter("idfilter", myFilter);

        MappingJacksonValue agenciesFilters = new MappingJacksonValue(allAgencies);

        agenciesFilters.setFilters(listOfFilters);
        return agenciesFilters;
    }
    //Get all regions
    @ApiOperation(value = "Get all regions")
    @GetMapping(value="/region")
    public MappingJacksonValue  regionList(){
        List<Region> allRegions = regionDAO.findAll();
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");

        FilterProvider listOfFilters = new SimpleFilterProvider().addFilter("idfilter", myFilter);

        MappingJacksonValue regionsFilters = new MappingJacksonValue(allRegions);

        regionsFilters.setFilters(listOfFilters);
        return regionsFilters;
    }
    //Get all stations
    @ApiOperation(value = "Get all Stations")
    @GetMapping(value="/station")
    public MappingJacksonValue stationList(){
        List<Station> allStations = stationDAO.findAll();
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");

        FilterProvider listOfFilters = new SimpleFilterProvider().addFilter("idvaluelistfilter", myFilter);

        MappingJacksonValue metricsFilters = new MappingJacksonValue(allStations);

        metricsFilters.setFilters(listOfFilters);
        return metricsFilters;
    }
    //Get all metrics
    @ApiOperation(value = "Get all metrics")
    @GetMapping(value = "/metric")
    public MappingJacksonValue metricList(){
        List<Metric> allMetrics = metricDAO.findAll();
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");

        FilterProvider listOfFilters = new SimpleFilterProvider().addFilter("idfilter", myFilter);

        MappingJacksonValue metricsFilters = new MappingJacksonValue(allMetrics);

        metricsFilters.setFilters(listOfFilters);
        return metricsFilters;
    }

    /* GET Requests to document by id*/

    //Get agency by ID
    @ApiOperation(value = "Get agency by ID if exists")
    @GetMapping(value="/agency/{id}")
    public Optional<Agency> agencybyID(@PathVariable UUID id){

        Optional<Agency> agency = agencyDAO.findById(id);
        if(agency == null) throw new AgencyNotFoundException(" Agency Not found");

        return agency;
    }
    //Get region by ID
    @ApiOperation(value = "Get region by ID if exists")
    @GetMapping(value="/region/{id}")
    public Optional<Region> regionbyID(@PathVariable UUID id){

        Optional<Region> region = regionDAO.findById(id);
        if(region==null) throw new RegionNotFoundException("Region Not found");

       return region;
    }
    //Get station by ID
    @ApiOperation(value = "Get station by ID if exists")
    @GetMapping(value="/station/{id}")
    public Optional<Station> stationbyID(@PathVariable UUID id){

        Optional<Station> station = stationDAO.findById(id);
        if (station == null) throw new StationNotFoundException("Station Not found");

       return station;
    }
    //Get metric by ID
    @ApiOperation(value = "Get metric by ID if exists")
    @GetMapping(value = "/metric/{id}")
    public Optional<Metric> metricbyID(@PathVariable UUID id){

        Optional<Metric> metric = metricDAO.findById(id);
        if (metric == null) throw new MetricNotFoundException("No data available for this Metric");

       return metric;
    }

    /*Requests to get documents base on input params*/

    //Get all regions from agency
    @ApiOperation(value = "Get all regions based on agency ID")
    @GetMapping(value = "/agency/{agencyID}/region")
    public List<Region> regionAgencyList(@PathVariable UUID agencyID){
        List<Region> allRegionsFromAgency = regionDAO.findByAgency(agencyID);
        return allRegionsFromAgency;
    }
    //Get all stations from agency
    @ApiOperation(value = "Get all stations based on agency ID")
    @GetMapping(value = "/agency/{agencyID}/station")
    public List<Station> stationAgencyList(@PathVariable UUID agencyID){
        List<Station> allStationsFromAgency = stationDAO.findByAgency(agencyID);
        return allStationsFromAgency;
    }

    /*filter stations based on input agency and region*/
    @ApiOperation(value = "Get all stations based on agency and region IDs")
    @GetMapping(value = "/region/{regionID}/agency/{agencyID}/station")
    public List<Station> stationAgencyRegionList(@PathVariable("regionID") UUID region,@PathVariable("agencyID") UUID agency){
        List<Station> allStationsFromRegionInAgency = stationDAO.findByRegionAndAgency(region,agency);
        return allStationsFromRegionInAgency;
    }

    /*POST requests to documents*/
    //save agencies to documents
    @ApiOperation(value = "save agency, ID is autogenerated")
    @PostMapping(value="/agency")
    public ResponseEntity<Void> saveAgency(@RequestBody Agency agency){
        Agency agencyAdded=null;
        if (agencyExist(agency.getName(),agency.getType(),agency.getUrl()))
            throw new AgencyExistsException("Agency already exists !");


        agency.setId(UUID.randomUUID());
        agencyAdded = agencyDAO.save(agency);

        if(agencyAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agencyAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
    //save regions to documents
    @ApiOperation(value = "save region, ID is autogenerated")
    @PostMapping(value = "/region")
    public  ResponseEntity<Void> saveRegion(@RequestBody Region region){
        Region regionAdded= null;

        if (regionExist(region.getName(),region.getType(),region.getAgency()))
            throw new RegionExistsException("Region already exists");

        region.setId(UUID.randomUUID());
         regionAdded = regionDAO.save(region);

        if(regionAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(regionAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //save metrics
    @ApiOperation(value = "save metric, ID is autogenerated")
    @PostMapping(value = "/metric")
    public ResponseEntity<Void>  saveMetric(@RequestBody Metric metric){
        Metric metricAdded = null;

        if (metricExist(metric.getName(),metric.getUnit()))
            throw new MetricExistsException("Metric already exists");

        metric.setId(UUID.randomUUID());
        metricAdded = metricDAO.save(metric);

        if(metricAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(metricAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //save stations
    @ApiOperation(value = "save station, ID is autogenerated")
    @PostMapping(value = "/station")
    public ResponseEntity<Void>  saveStation(@RequestBody Station station){
        Station stationAdded= null;
        UUID idgenerated = UUID.randomUUID();

        if (stationExist(station.getAgency(),station.getRegion(),station.getName()))
            throw  new StationExistsException("Station already exists");

        station.setId(idgenerated);
        stationAdded = stationDAO.save(station);

        if(stationAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(stationAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*PUT requests to documents*/
    @ApiOperation(value = "Update agency using ID")
    @PutMapping(value= "/agency/{id}")
    public ResponseEntity<Void> updateAgency( @RequestBody Agency agency,@PathVariable UUID id){

        Optional<Agency> optionalAgency = agencyDAO.findById(id);

        if (!optionalAgency.isPresent())
            return  ResponseEntity.notFound().build();
        agency.setId(id);
        agencyDAO.save(agency);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update region using ID")
    @PutMapping(value= "/region/{id}")
    public ResponseEntity<Void> updateRegion( @RequestBody Region region,@PathVariable UUID id){

        Optional<Region> optionalRegion = regionDAO.findById(id);

        if (!optionalRegion.isPresent())
            return  ResponseEntity.notFound().build();
        region.setId(id);
        regionDAO.save(region);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update metric using ID")
    @PutMapping(value= "/metric/{id}")
    public ResponseEntity<Void> updateRegion( @RequestBody Metric metric,@PathVariable UUID id){

        Optional<Metric> optionalMetric = metricDAO.findById(id);

        if (!optionalMetric.isPresent())
            return  ResponseEntity.notFound().build();
        metric.setId(id);
        metricDAO.save(metric);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update station using ID")
    @PutMapping(value= "/station/{id}")
    public ResponseEntity<Void> updateRegion( @RequestBody Station station,@PathVariable UUID id){

        Optional<Station> optionalStation = stationDAO.findById(id);

        if (!optionalStation.isPresent())
            return  ResponseEntity.notFound().build();
        station.setId(id);
        stationDAO.save(station);

        return ResponseEntity.noContent().build();
    }


    /*DELETE requests to documents*/
    @ApiOperation(value = "Delete agency using ID")
    @DeleteMapping("/agency/{id}")
    public void deleteAgency(@PathVariable UUID id) {
        agencyDAO.deleteById(id);
    }

    @ApiOperation(value = "Delete region using ID")
    @DeleteMapping("/region/{id}")
    public void deleteRegion(@PathVariable UUID id) {
        regionDAO.deleteById(id);
    }

    @ApiOperation(value = "Delete metric using ID")
    @DeleteMapping("/metric/{id}")
    public void deleteMetric(@PathVariable UUID id) {
        metricDAO.deleteById(id);
    }

    @ApiOperation(value = "Delete station using ID")
    @DeleteMapping("/station/{id}")
    public void deleteStation(@PathVariable UUID id) {
        stationDAO.deleteById(id);
    }
    /*Utils methods to avoid duplicate information */

    private boolean agencyExist(String name,String type,String url) {
        Agency agency = agencyDAO.findByNameAndTypeAndUrl(name,type,url);
        if (agency != null) {
            return true;
        }
        return false;
    }

    private boolean regionExist(String name,String type,UUID agency) {
        Region region = regionDAO.findByNameAndTypeAndAgency(name,type,agency);
        if (region != null) {
            return true;
        }
        return false;
    }

    private boolean metricExist(String name,String unit) {
        Metric metric = metricDAO.findByNameAndUnit(name,unit);
        if (metric != null) {
            return true;
        }
        return false;
    }

    private boolean stationExist(UUID agency,UUID region, String name) {
        Station station = stationDAO.findByAgencyAndRegionAndName(agency,region,name);
        if (station != null) {
            return true;
        }
        return false;
    }
}
