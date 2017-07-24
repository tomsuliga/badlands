package org.suliga.badlands.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.suliga.badlands.model.Image;

public interface ImageDao extends PagingAndSortingRepository<Image, Long> {

}
