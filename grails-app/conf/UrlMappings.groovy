class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        //Only define rules for books, rules for author used Grails auto urlmapping builder
        "/api/book.$format"(controller:"book"){
            action = [POST:"save"]
        }
        "/api/book/$id.$format"(controller:"book"){
            action = [GET:"show",PUT:"update", DELETE:"delete"]
        }
        "/api/author/$id/book.$format"(controller:"book"){
            action = [GET:"listByAuthor"]
        }

        // Custom controller with some constraints
        "/api/custom/$bookId/$action.$format" {
            controller = "restCustom"
            constraints {
                bookId nullable: false, blank: false
            }
        }
        "/api/custom/$bookId/page/$pageId/$action.$format" {
            controller = "restCustom"
            constraints {
                bookId nullable: false, blank: false
                pageId nullable: false, blank: false
            }
        }
        // Custom + grouping
        group "/public", {
            "/a"(controller:"restCustom", action:"a", format: "json")
            "/b/$fullname/$numberOfBook.$format"(controller:"restCustom", action:"b") {
                constraints {
                    fullname nullable: false, blank: false
                    numberOfBook nullable: false, blank: false
                }
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
