package com.msr.data;

import com.msr.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A sample JPA repository for querying and storing sites
 */
public interface SiteRepository extends PagingAndSortingRepository<Site, Integer> {
}