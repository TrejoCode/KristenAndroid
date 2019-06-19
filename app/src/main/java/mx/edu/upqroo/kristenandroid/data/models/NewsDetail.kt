package mx.edu.upqroo.kristenandroid.data.models

class NewsDetail(var newsId: String?, var url: String?, var title: String?, var description: String?, var imageCover: String?,
                 var categories: String?, var date: String?, var newsTypeId: Int, var author: String?,
                 var contentList: List<Content>?)
