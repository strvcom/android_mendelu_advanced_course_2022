import 'package:get/get.dart';
import 'package:xkcd/core_network/utils/network_utils.dart';
import 'package:xkcd/core_comics/data/dto/comics_dto.dart';

/// https://any-api.com/xkcd_com/xkcd_com/docs/Definitions/comic
class XKCDProvider extends GetConnect implements GetxService {
  XKCDProvider() {
    NetworkUtils.applyLoggingModifiers(httpClient);
    httpClient.baseUrl = 'https://xkcd.com/';
    httpClient.defaultDecoder = ComicsDTO.fromJson;
  }

  Future<ComicsDTO> getComics(int comicsId) async => (await get('$comicsId/info.0.json')).body;
}
