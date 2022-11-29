class ComicsDTO {
  ComicsDTO(
    this.alt,
    this.day,
    this.img,
    this.link,
    this.month,
    this.news,
    this.num,
    this.safeTitle,
    this.title,
    this.transcript,
    this.year,
  );

  String alt;
  String day;
  String img;
  String link;
  String month;
  String news;
  int num;
  String safeTitle;
  String title;
  String transcript;
  String year;

  static ComicsDTO fromJson(dynamic data) {
    Map<String, dynamic> json = data as Map<String, dynamic>;
    return ComicsDTO(
      json["alt"],
      json["day"],
      json["img"],
      json["link"],
      json["month"],
      json["news"],
      json["num"],
      json["safe_title"],
      json["title"],
      json["transcript"],
      json["year"],
    );
  }
}
