package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "GoodreadsResponse")
class BookResponse {
    @Element
    var book: BookDetails? = null

}

@Xml(name = "book")
class BookDetails {
    @PropertyElement
    var id: String? = null
    @PropertyElement
    var title: String? = null
    @PropertyElement
    var isbn: String? = null
    @PropertyElement
    var isbn13: String? = null
    @PropertyElement
    var asin: String? = null
    @PropertyElement
    var kindle_asin: String? = null
    @PropertyElement
    var marketplace_id: String? = null
    @PropertyElement
    var country_code: String? = null
    @PropertyElement
    var image_url: String? = null
    @PropertyElement
    var small_image_url: String? = null
    @PropertyElement
    var publication_year: String? = null
    @PropertyElement
    var publication_month: String? = null
    @PropertyElement
    var publication_day: String? = null
    @PropertyElement
    var publisher: String? = null
    @PropertyElement
    var language_code: String? = null

    @PropertyElement(name="is_ebook")
    var isEbook: Boolean? = null

    @PropertyElement
    var description: String? = null
    @Element
    var work: Work? = null
    @PropertyElement
    var average_rating: String? = null
    @PropertyElement
    var num_pages: String? = null
    @PropertyElement
    var format: String? = null
    @PropertyElement
    var edition_information: String? = null
    @PropertyElement
    var ratings_count: String? = null
    @PropertyElement
    var text_reviews_count: String? = null
    @PropertyElement
    var url: String? = null
    @PropertyElement
    var link: String? = null
    @Element
    var authors:Authors? = null
//    @PropertyElement
//    var reviews_widget: String? = null

    @Element
    var book_links: BookLinks? = null
//
//    @PropertyElement
//    var series_works: String? = null

//    @PropertyElement
//    var popular_shelves: String? = null

    @Element
    var buy_links: List<BuyLink>? =
        null
    @Element
    var similar_books: List<BookDetails>? = null



}

@Xml(name = "work")
class Work {
    @PropertyElement
    var id: String? = null
    @PropertyElement
    var books_count: String? = null
    @PropertyElement
    var best_book_id: String? = null
    @PropertyElement
    var reviews_count: String? = null
    @PropertyElement
    var ratings_sum: String? = null
    @PropertyElement
    var ratings_count: String? = null
    @PropertyElement
    var text_reviews_count: String? = null
    @PropertyElement
    var original_publication_year: String? = null
    @PropertyElement
    var original_publication_month: String? = null
    @PropertyElement
    var original_publication_day: String? = null
    @PropertyElement
    var original_title: String? = null
    @PropertyElement
    var original_language_id: String? = null
    @PropertyElement
    var media_type: String? = null
    @PropertyElement
    var rating_dist: String? = null
    @PropertyElement
    var desc_user_id: String? = null
    @PropertyElement
    var default_chaptering_book_id: String? = null
    @PropertyElement
    var default_description_language_code: String? = null
    @PropertyElement
    var work_uri: String? = null

}

@Xml(name = "authors")
class Authors {

    @Element
    var author: Author? =
        null


}
@Xml(name = "author")
class Author {

    @PropertyElement
    var id: String? = null
    @PropertyElement
    var name: String? = null
    @PropertyElement
    var role: String? = null
    @PropertyElement
    var image_url: String? = null
    @PropertyElement
    var small_image_url: String? = null
    @PropertyElement
    var link: String? = null
    @PropertyElement
    var average_rating: String? = null
    @PropertyElement
    var ratings_count: String? = null
    @PropertyElement
    var text_reviews_count: String? = null

}
@Xml(name = "book_links")
class BookLinks {
    @Element
    var book_link: BookLink? =
        null


}
@Xml(name = "book_link")
class BookLink {
    var id: String? = null
    var name: String? = null
    var link: String? = null

}
@Xml(name = "buy_link")
class BuyLink {
    var id: String? = null
    var name: String? = null
    var link: String? = null

}
