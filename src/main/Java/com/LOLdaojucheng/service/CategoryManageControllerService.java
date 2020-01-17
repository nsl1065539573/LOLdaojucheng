package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;

public interface CategoryManageControllerService {
    ServerResponse get_category(Integer categoryid);

    ServerResponse add_category(Integer parentId, String categoryName);

    ServerResponse change_category(Integer categorytId, String categoryName);

    ServerResponse get_every_category(Integer categorytId);
    ServerResponse selectAll();
}
