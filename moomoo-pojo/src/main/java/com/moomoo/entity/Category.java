package com.moomoo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // Category Type: 1 Ice Cream Items Category 2 Combo Category
    private Integer type;

    // Category Name
    private String name;

    // Sort Order
    private Integer sort;

    // Category Status: 0 Disabled 1 Enabled
    private Integer status;

    // Create Time
    private LocalDateTime createTime;

    // Update Time
    private LocalDateTime updateTime;

    // Creator ID
    private Long createUser;

    // Updater ID
    private Long updateUser;
}
