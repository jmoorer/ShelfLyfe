package com.moor.shelflyfe.api.gr2

// result generated from /xml

data class Author(val role: String?, val small_image_url: Small_image_url?, val text_reviews_count: String?, val image_url: Image_url?, val name: String?, val link: String?, val average_rating: String?, val id: String?, val ratings_count: String?)

data class Authors(val author: Author?)

data class Base(val GoodreadsResponse: GoodreadsResponse?)

data class Best_book_id(val type: String?, val content: String?)

data class Book(val marketplace_id: String?, val book_links: Book_links?, val isbn: String?, val num_pages: String?, val link: String?, val description: String?, val title: String?, val language_code: String?, val small_image_url: String?, val text_reviews_count: String?, val series_works: String?, val is_ebook: String?, val edition_information: String?, val id: String?, val ratings_count: String?, val reviews_widget: String?, val image_url: String?, val work: Work?, val format: String?, val popular_shelves: Popular_shelves?, val average_rating: String?, val buy_links: Buy_links?, val url: String?, val country_code: String?, val publication_month: String?, val publication_year: String?, val similar_books: Similar_books?, val isbn13: String?, val publisher: String?, val asin: String?, val publication_day: String?, val kindle_asin: String?, val authors: Authors?)

data class Book1809337991(val image_url: String?, val work: Work?, val num_pages: String?, val isbn: String?, val link: String?, val average_rating: String?, val title: String?, val publication_month: String?, val publication_year: String?, val small_image_url: String?, val isbn13: String?, val publication_day: String?, val id: String?, val ratings_count: String?, val title_without_series: String?, val authors: Authors?)

data class Book_link(val name: String?, val link: String?, val id: String?)

data class Book_links(val book_link: Book_link?)

data class Books_count(val type: String?, val content: String?)

data class Buy_link1679462508(val name: String?, val link: String?, val id: String?)

data class Buy_links(val buy_link: List<Buy_link1679462508>?)

data class Default_chaptering_book_id(val nil: String?, val type: String?)

data class Default_description_language_code(val nil: String?)

data class Desc_user_id(val type: String?, val content: String?)

data class GoodreadsResponse(val book: Book?, val Request: Request?)

data class Id(val type: String?, val content: String?)

data class Image_url(val nophoto: String?, val content: String?)

data class Original_language_id(val nil: String?, val type: String?)

data class Original_publication_day(val type: String?, val content: String?)

data class Original_publication_month(val type: String?, val content: String?)

data class Original_publication_year(val type: String?, val content: String?)

data class Popular_shelves(val shelf: List<Shelf1225891054>?)

data class Ratings_count(val type: String?, val content: String?)

data class Ratings_sum(val type: String?, val content: String?)

data class Request(val method: String?, val key: String?, val authentication: String?)

data class Reviews_count(val type: String?, val content: String?)

data class Shelf1225891054(val count: String?, val name: String?)

data class Similar_books(val book: List<Book1809337991>?)

data class Small_image_url(val nophoto: String?, val content: String?)

data class Text_reviews_count(val type: String?, val content: String?)

data class Work(val default_description_language_code: Default_description_language_code?, val books_count: Books_count?, val original_publication_month: Original_publication_month?, val original_title: String?, val work_uri: String?, val original_publication_year: Original_publication_year?, val rating_dist: String?, val original_language_id: Original_language_id?, val best_book_id: Best_book_id?, val text_reviews_count: Text_reviews_count?, val media_type: String?, val original_publication_day: Original_publication_day?, val default_chaptering_book_id: Default_chaptering_book_id?, val ratings_sum: Ratings_sum?, val id: Id?, val ratings_count: Ratings_count?, val reviews_count: Reviews_count?, val desc_user_id: Desc_user_id?)
