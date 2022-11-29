import 'package:flutter_fimber/flutter_fimber.dart';
import 'package:get/get.dart';

class NetworkUtils {
  static applyLoggingModifiers(GetHttpClient httpClient) {
    httpClient.addRequestModifier<dynamic>((request) {
      Fimber.d("[Request] ----- [${request.method.toUpperCase()}] ${request.url} -----");
      Fimber.d("[Request] Headers: ${request.headers}");
      return request;
    });

    httpClient.addResponseModifier((request, response) {
      Fimber.d("[Response] ----- [${request.method.toUpperCase()}] ${request.url} -----");
      Fimber.d("[Response] StatusCode: ${response.statusCode} / ${response.statusText}");
      Fimber.d("[Response] Body: ${response.bodyString}");
      return response;
    });
  }
}
