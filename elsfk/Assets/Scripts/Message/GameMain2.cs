// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GameMain2.proto
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
/// <summary>Holder for reflection information generated from GameMain2.proto</summary>
public static partial class GameMain2Reflection {

  #region Descriptor
  /// <summary>File descriptor for GameMain2.proto</summary>
  public static pbr::FileDescriptor Descriptor {
    get { return descriptor; }
  }
  private static pbr::FileDescriptor descriptor;

  static GameMain2Reflection() {
    byte[] descriptorData = global::System.Convert.FromBase64String(
        string.Concat(
          "Cg9HYW1lTWFpbjIucHJvdG8iSwoJZ2FtZWRhdGEyEiMKBWRhdGFzGAEgAygL",
          "MhQuZ2FtZWRhdGEyLmdhbWVkYXRhMRoZCglnYW1lZGF0YTESDAoEZGF0YRgB",
          "IAMoBUIYChRjb20ubWEudGVzdC5wcm90b2J1ZlABYgZwcm90bzM="));
    descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
        new pbr::FileDescriptor[] { },
        new pbr::GeneratedClrTypeInfo(null, new pbr::GeneratedClrTypeInfo[] {
          new pbr::GeneratedClrTypeInfo(typeof(global::gamedata2), global::gamedata2.Parser, new[]{ "Datas" }, null, null, new pbr::GeneratedClrTypeInfo[] { new pbr::GeneratedClrTypeInfo(typeof(global::gamedata2.Types.gamedata1), global::gamedata2.Types.gamedata1.Parser, new[]{ "Data" }, null, null, null)})
        }));
  }
  #endregion

}
#region Messages
public sealed partial class gamedata2 : pb::IMessage<gamedata2> {
  private static readonly pb::MessageParser<gamedata2> _parser = new pb::MessageParser<gamedata2>(() => new gamedata2());
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public static pb::MessageParser<gamedata2> Parser { get { return _parser; } }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public static pbr::MessageDescriptor Descriptor {
    get { return global::GameMain2Reflection.Descriptor.MessageTypes[0]; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  pbr::MessageDescriptor pb::IMessage.Descriptor {
    get { return Descriptor; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata2() {
    OnConstruction();
  }

  partial void OnConstruction();

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata2(gamedata2 other) : this() {
    datas_ = other.datas_.Clone();
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata2 Clone() {
    return new gamedata2(this);
  }

  /// <summary>Field number for the "datas" field.</summary>
  public const int DatasFieldNumber = 1;
  private static readonly pb::FieldCodec<global::gamedata2.Types.gamedata1> _repeated_datas_codec
      = pb::FieldCodec.ForMessage(10, global::gamedata2.Types.gamedata1.Parser);
  private readonly pbc::RepeatedField<global::gamedata2.Types.gamedata1> datas_ = new pbc::RepeatedField<global::gamedata2.Types.gamedata1>();
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public pbc::RepeatedField<global::gamedata2.Types.gamedata1> Datas {
    get { return datas_; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override bool Equals(object other) {
    return Equals(other as gamedata2);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public bool Equals(gamedata2 other) {
    if (ReferenceEquals(other, null)) {
      return false;
    }
    if (ReferenceEquals(other, this)) {
      return true;
    }
    if(!datas_.Equals(other.datas_)) return false;
    return true;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override int GetHashCode() {
    int hash = 1;
    hash ^= datas_.GetHashCode();
    return hash;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override string ToString() {
    return pb::JsonFormatter.ToDiagnosticString(this);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public void WriteTo(pb::CodedOutputStream output) {
    datas_.WriteTo(output, _repeated_datas_codec);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public int CalculateSize() {
    int size = 0;
    size += datas_.CalculateSize(_repeated_datas_codec);
    return size;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public void MergeFrom(gamedata2 other) {
    if (other == null) {
      return;
    }
    datas_.Add(other.datas_);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public void MergeFrom(pb::CodedInputStream input) {
    uint tag;
    while ((tag = input.ReadTag()) != 0) {
      switch(tag) {
        default:
          input.SkipLastField();
          break;
        case 10: {
          datas_.AddEntriesFrom(input, _repeated_datas_codec);
          break;
        }
      }
    }
  }

  #region Nested types
  /// <summary>Container for nested types declared in the gamedata2 message type.</summary>
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public static partial class Types {
    public sealed partial class gamedata1 : pb::IMessage<gamedata1> {
      private static readonly pb::MessageParser<gamedata1> _parser = new pb::MessageParser<gamedata1>(() => new gamedata1());
      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public static pb::MessageParser<gamedata1> Parser { get { return _parser; } }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public static pbr::MessageDescriptor Descriptor {
        get { return global::gamedata2.Descriptor.NestedTypes[0]; }
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      pbr::MessageDescriptor pb::IMessage.Descriptor {
        get { return Descriptor; }
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public gamedata1() {
        OnConstruction();
      }

      partial void OnConstruction();

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public gamedata1(gamedata1 other) : this() {
        data_ = other.data_.Clone();
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public gamedata1 Clone() {
        return new gamedata1(this);
      }

      /// <summary>Field number for the "data" field.</summary>
      public const int DataFieldNumber = 1;
      private static readonly pb::FieldCodec<int> _repeated_data_codec
          = pb::FieldCodec.ForInt32(10);
      private readonly pbc::RepeatedField<int> data_ = new pbc::RepeatedField<int>();
      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public pbc::RepeatedField<int> Data {
        get { return data_; }
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public override bool Equals(object other) {
        return Equals(other as gamedata1);
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public bool Equals(gamedata1 other) {
        if (ReferenceEquals(other, null)) {
          return false;
        }
        if (ReferenceEquals(other, this)) {
          return true;
        }
        if(!data_.Equals(other.data_)) return false;
        return true;
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public override int GetHashCode() {
        int hash = 1;
        hash ^= data_.GetHashCode();
        return hash;
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public override string ToString() {
        return pb::JsonFormatter.ToDiagnosticString(this);
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public void WriteTo(pb::CodedOutputStream output) {
        data_.WriteTo(output, _repeated_data_codec);
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public int CalculateSize() {
        int size = 0;
        size += data_.CalculateSize(_repeated_data_codec);
        return size;
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public void MergeFrom(gamedata1 other) {
        if (other == null) {
          return;
        }
        data_.Add(other.data_);
      }

      [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
      public void MergeFrom(pb::CodedInputStream input) {
        uint tag;
        while ((tag = input.ReadTag()) != 0) {
          switch(tag) {
            default:
              input.SkipLastField();
              break;
            case 10:
            case 8: {
              data_.AddEntriesFrom(input, _repeated_data_codec);
              break;
            }
          }
        }
      }

    }

  }
  #endregion

}

#endregion


#endregion Designer generated code
