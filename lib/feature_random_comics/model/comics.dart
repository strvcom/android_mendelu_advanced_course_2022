import 'package:xkcd/core_comics/data/dto/comics_dto.dart';

class Comics {
  Comics(
    this.title,
    this.description,
    this.year,
    this.imageUrl,
  );

  String title;
  String description;
  String year;
  String imageUrl;

  static Comics fromDto(ComicsDTO dto) {
    return Comics(
      dto.safeTitle,
      dto.alt,
      dto.year,
      dto.img,
    );
  }
}
