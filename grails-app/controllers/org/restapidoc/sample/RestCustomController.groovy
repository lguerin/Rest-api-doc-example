package org.restapidoc.sample

import grails.converters.JSON
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.pojo.RestApiParamType
import org.restapidoc.pojo.RestApiVerb

/**
 * Created by lguerin on 03/06/15.
 */
@RestApi(name = "Rest custom controller", description = "Rest methods for a custom controller")
class RestCustomController {

    static responseFormats = ['json']
    static allowedMethods = [add: "POST", show: "GET", a: "GET", b: "GET"]

    @RestApiMethod(description="Add a book to the store", verb = RestApiVerb.POST)
    @RestApiParams(params=[
            @RestApiParam(name="bookId", type="long", paramType = RestApiParamType.PATH, description = "The book id")
    ])
    @RestApiResponseObject(objectIdentifier="")
    def add(Long bookId) {
        def json = [:]
        json['method'] = "add"
        json['bookId'] = bookId
        render (json as JSON).toString()
    }

    @RestApiMethod(description="Display a page of a book", verb = RestApiVerb.GET)
    @RestApiParams(params=[
            @RestApiParam(name="bookId", type="long", paramType = RestApiParamType.PATH, description = "The book id"),
            @RestApiParam(name="pageId", type="long", paramType = RestApiParamType.PATH, description = "The page id of the book")
    ])
    @RestApiResponseObject(objectIdentifier="")
    def show(Long bookId, Long pageId) {
        def json = [:]
        json['method'] = "show"
        json['bookId'] = bookId
        json['pageId'] = pageId
        render (json as JSON).toString()
    }

    @RestApiMethod(description="Custom grouping method a", verb = RestApiVerb.GET)
    @RestApiResponseObject(objectIdentifier="")
    def a() {
        def json = [:]
        json['method'] = "a"
        render (json as JSON).toString()
    }

    @RestApiMethod(description="Custom grouping method b with param", verb = RestApiVerb.GET)
    @RestApiParams(params=[
            @RestApiParam(name="fullname", type="string", paramType = RestApiParamType.PATH, description = "Author fullname"),
            @RestApiParam(name="numberOfBook", type="integer", paramType = RestApiParamType.PATH, description = "Number of books")
    ])
    @RestApiResponseObject(objectIdentifier="[stats]")
    def b(String fullname, Integer numberOfBook) {
        render ([fullname:fullname, numberOfBook: numberOfBook] as JSON).toString()
    }
}
