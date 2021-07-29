package com.redner.postapp.data.dto.transform

import com.redner.postapp.data.dto.response.PostDto
import com.redner.postapp.data.model.Post


fun PostDto.transform(): Post =
    Post(
        userId = userId,
        id = id,
        title = title,
        body = body,
    )