import 'package:get/get.dart';

class RandomComicsContoller extends GetxController {
  // Rxn<Comics> comics = Rxn();

  @override
  void onInit() async {
    super.onInit();

    await loadData();
  }

  Future<void> loadData() async {
    // final xkcdProvider = Get.find<XKCDProvider>();
    // final randomComicsId = Random().nextInt(2700); // There is currently around 2700 comics

    // ComicsDTO comicsDto = await xkcdProvider.getComics(randomComicsId);
    // Comics comicsModel = Comics.fromDto(comicsDto);

    // Fimber.d("Comics title: ${comicsModel.title}");
    // Fimber.d("Comics description: ${comicsModel.description}");
    // Fimber.d("Comics year: ${comicsModel.year}");
    // Fimber.d("Comics imageUrl: ${comicsModel.imageUrl}");

    // comics.value = comicsModel;
  }
}
