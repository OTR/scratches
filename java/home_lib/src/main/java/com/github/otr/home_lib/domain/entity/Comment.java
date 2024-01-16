package com.github.otr.home_lib.domain.entity;

import java.time.LocalDateTime;

/**
 * POJO class for domain entity that represents a single user Comment in
 * out bullet-in board
 *
 * @param id unique identifier, required to distinguish entities within a repository
 * @param created A local DateTime at which the comment was created
 * @param text contents of the comment
 */
public record Comment(
        int id,
        LocalDateTime created,
        String text
) {}
