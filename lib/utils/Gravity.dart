class Gravity {
  static const int NoGravity = 0x0000;
  static const int AxisSpecified = 0x0001;
  static const int AxisPullBefore = 0x0002;
  static const int AxisPullAfter = 0x0004;
  static const int AxisClip = 0x00000008;
  static const int AxisXShift = 0;
  static const int AxisYShift = 4;
  static const int TOP = 0x00000030;
  static const int Bottom = 0x00000050;
  static const int Left = 0x00000003;
  static const int Right = 0x00000005;
  static const int CenterVertical = 0x00000010;
  static const int FillVertical = 0x00000070;
  static const int CenterHorizontal = 0x00000001;
  static const int FillHorizontal = 0x00000007;
  static const int Center = 0x00000011;
  static const int Fill = 0x00000077;
  static const int ClipVertical = 0x00000080;
  static const int ClipHorizontal = 0x00000008;
  static const int RelativeLayoutDirection = 0x00800000;
  static const int HorizontalGravityMask = 0x00000007;
  static const int VerticalGravityMask = 0x00000070;
  static const int DisplayClipVertical = 0x10000000;
  static const int DisplayClipHorizontal = 0x01000000;
  static const int Start = 0x00800003;
  static const int End = 0x00800005;
  static const int RelativeHorizontalGravityMask = 0x00800007;
}
