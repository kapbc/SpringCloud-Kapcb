package com.kapcb.ccc.model;

import com.kapcb.ccc.model.base.BaseTree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <a>Title: TestMenu </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/1 22:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestMenu extends BaseTree<TestMenu> {

    private String menuName;

    private String menuIcon;

    private String menuPath;
}
