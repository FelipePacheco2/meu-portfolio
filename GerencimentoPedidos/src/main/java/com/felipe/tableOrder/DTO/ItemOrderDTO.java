package com.felipe.tableOrder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ItemOrderDTO {
    private Long idMenu;
    private int quantity;
}
