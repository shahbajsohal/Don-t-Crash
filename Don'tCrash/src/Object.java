abstract class Object{
  double speed;
  String fileName;
  int width;
  int height;

  abstract int getHeight();
  abstract int getWidth();
  abstract String getFileName();
  abstract void update();
}