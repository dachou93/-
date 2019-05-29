// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GameMain.proto
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
/// <summary>Holder for reflection information generated from GameMain.proto</summary>
public static partial class GameMainReflection {

  #region Descriptor
  /// <summary>File descriptor for GameMain.proto</summary>
  public static pbr::FileDescriptor Descriptor {
    get { return descriptor; }
  }
  private static pbr::FileDescriptor descriptor;

  static GameMainReflection() {
    byte[] descriptorData = global::System.Convert.FromBase64String(
        string.Concat(
          "Cg5HYW1lTWFpbi5wcm90byI1CghnYW1lZGF0YRILCgNyb3cYASABKAUSDgoG",
          "Y29sdW1uGAIgASgFEgwKBGRhdGEYAyADKAVCGAoUY29tLm1hLnRlc3QucHJv",
          "dG9idWZQAWIGcHJvdG8z"));
    descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
        new pbr::FileDescriptor[] { },
        new pbr::GeneratedClrTypeInfo(null, new pbr::GeneratedClrTypeInfo[] {
          new pbr::GeneratedClrTypeInfo(typeof(global::gamedata), global::gamedata.Parser, new[]{ "Row", "Column", "Data" }, null, null, null)
        }));
  }
  #endregion

}
#region Messages
public sealed partial class gamedata : pb::IMessage<gamedata> {
  private static readonly pb::MessageParser<gamedata> _parser = new pb::MessageParser<gamedata>(() => new gamedata());
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public static pb::MessageParser<gamedata> Parser { get { return _parser; } }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public static pbr::MessageDescriptor Descriptor {
    get { return global::GameMainReflection.Descriptor.MessageTypes[0]; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  pbr::MessageDescriptor pb::IMessage.Descriptor {
    get { return Descriptor; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata() {
    OnConstruction();
  }

  partial void OnConstruction();

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata(gamedata other) : this() {
    row_ = other.row_;
    column_ = other.column_;
    data_ = other.data_.Clone();
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public gamedata Clone() {
    return new gamedata(this);
  }

  /// <summary>Field number for the "row" field.</summary>
  public const int RowFieldNumber = 1;
  private int row_;
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public int Row {
    get { return row_; }
    set {
      row_ = value;
    }
  }

  /// <summary>Field number for the "column" field.</summary>
  public const int ColumnFieldNumber = 2;
  private int column_;
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public int Column {
    get { return column_; }
    set {
      column_ = value;
    }
  }

  /// <summary>Field number for the "data" field.</summary>
  public const int DataFieldNumber = 3;
  private static readonly pb::FieldCodec<int> _repeated_data_codec
      = pb::FieldCodec.ForInt32(26);
  private readonly pbc::RepeatedField<int> data_ = new pbc::RepeatedField<int>();
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public pbc::RepeatedField<int> Data {
    get { return data_; }
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override bool Equals(object other) {
    return Equals(other as gamedata);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public bool Equals(gamedata other) {
    if (ReferenceEquals(other, null)) {
      return false;
    }
    if (ReferenceEquals(other, this)) {
      return true;
    }
    if (Row != other.Row) return false;
    if (Column != other.Column) return false;
    if(!data_.Equals(other.data_)) return false;
    return true;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override int GetHashCode() {
    int hash = 1;
    if (Row != 0) hash ^= Row.GetHashCode();
    if (Column != 0) hash ^= Column.GetHashCode();
    hash ^= data_.GetHashCode();
    return hash;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public override string ToString() {
    return pb::JsonFormatter.ToDiagnosticString(this);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public void WriteTo(pb::CodedOutputStream output) {
    if (Row != 0) {
      output.WriteRawTag(8);
      output.WriteInt32(Row);
    }
    if (Column != 0) {
      output.WriteRawTag(16);
      output.WriteInt32(Column);
    }
    data_.WriteTo(output, _repeated_data_codec);
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public int CalculateSize() {
    int size = 0;
    if (Row != 0) {
      size += 1 + pb::CodedOutputStream.ComputeInt32Size(Row);
    }
    if (Column != 0) {
      size += 1 + pb::CodedOutputStream.ComputeInt32Size(Column);
    }
    size += data_.CalculateSize(_repeated_data_codec);
    return size;
  }

  [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
  public void MergeFrom(gamedata other) {
    if (other == null) {
      return;
    }
    if (other.Row != 0) {
      Row = other.Row;
    }
    if (other.Column != 0) {
      Column = other.Column;
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
        case 8: {
          Row = input.ReadInt32();
          break;
        }
        case 16: {
          Column = input.ReadInt32();
          break;
        }
        case 26:
        case 24: {
          data_.AddEntriesFrom(input, _repeated_data_codec);
          break;
        }
      }
    }
  }

}

#endregion


#endregion Designer generated code
