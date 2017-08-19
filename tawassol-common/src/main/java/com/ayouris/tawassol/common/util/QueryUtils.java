package com.ayouris.tawassol.common.util;

import com.querydsl.jpa.impl.JPAQuery;

public abstract class QueryUtils {

    /**
     * Add pagination to query: limit and offset
     * 
     * @param query
     *            JPA query
     * @param pageNumber
     *            page number
     * @param pageSize
     *            page size
     */
    public static void applyPagination(JPAQuery query, Integer pageNumber, Integer pageSize) {
        if (pageSize != null) {
            if (isValidPageParam(pageSize)) {
                query = (JPAQuery) query.limit(pageSize.longValue()); 
            }
            if (isValidPageParam(pageNumber)) {
                query.offset((pageNumber.intValue() - 1) * pageSize.intValue());
            }
        }
    }

    /**
     * Check if paginate param is valid
     * 
     * @param pageParam
     *            may be either pageNumber or pageSize
     * @return true if valid
     */
    private static boolean isValidPageParam(Integer pageParam) {
        return pageParam != null && pageParam.intValue() >= 1;
    }

}
