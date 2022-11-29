import 'package:flutter/material.dart';
import 'package:get/get.dart';

class GetXHomePage extends GetView<GetXHomeController> {
  const GetXHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Getx Home Page sample"),
      ),
      body: Center(
        child: Obx(() => Text('Click count: ${controller.counter.value}')),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: controller.incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}

class GetXHomeController extends GetxController {
  RxInt counter = RxInt(0);

  void incrementCounter() {
    counter++;
  }
}
