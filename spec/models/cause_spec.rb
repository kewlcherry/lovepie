require 'spec_helper'

describe Cause do
  before(:each) do
    @valid_attributes = {
            :name => "value for name"
    }
  end

  it "should create a new instance given valid attributes" do
    Cause.create!(@valid_attributes)
  end

  it "should be sorted by most used cause" do
    one = Cause.make
    two = Cause.make
    three = Cause.make
    5.times {Organisation.make << one}
    3.times {Organisation.make << two}
    2.times {Organisation.make << three}
    Cause.count should eql? 3
  end
end
