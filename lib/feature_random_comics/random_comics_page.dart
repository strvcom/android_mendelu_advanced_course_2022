import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:xkcd/feature_random_comics/random_comics_controller.dart';

class RandomComicsPage extends GetView<RandomComicsContoller> {
  const RandomComicsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Random Comics"),
      ),
      // body: Center(
      //   child: CircularProgressIndicator.adaptive(),
      // ),
      body: const _ContentWidget(),
    );
  }
}

class _ContentWidget extends GetView<RandomComicsContoller> {
  const _ContentWidget({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Obx(
        () => Padding(
          padding: const EdgeInsets.all(20),
          child: (controller.comics.value == null)
              ? const Center(child: CircularProgressIndicator())
              : SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(controller.comics.value!.title, style: Theme.of(context).textTheme.displaySmall),
                      const SizedBox(height: 8),
                      Text(controller.comics.value!.year, style: Theme.of(context).textTheme.labelMedium),
                      const SizedBox(height: 16),
                      Text(controller.comics.value!.description, style: Theme.of(context).textTheme.bodyMedium),
                      const SizedBox(height: 32),
                      Center(child: Image.network(controller.comics.value!.imageUrl)),
                      const SizedBox(height: 32),
                      Center(child: ElevatedButton(onPressed: () => controller.loadData(), child: const Text("Next"))),
                    ],
                  ),
                ),
        ),
      ),
    );
  }
}
