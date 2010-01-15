require 'spec_helper'

describe Cause do
  before(:each) do
    @user = User.make
    @organisation = Organisation.make
  end

  it "should create a new instance given valid attributes" do
    Cause.make
  end

  it "should not create 2 causes with the same organisation and user" do
    lambda {
      2.times{ Cause.make(:name => "test", :organisation => @organisation, :user => @user) }
    }.should raise_error
    Cause.count.should eql 1
  end

  it "should limit the user to add a maximum of 3 causes to an organisation" do
    lambda {
      5.times{ Cause.make(:organisation => @organisation, :user => @user) }
    }.should raise_error(ActiveRecord::RecordInvalid, "Validation failed: You cannot have more than 3 causes per organisation.")                
    Cause.count.should eql 3
  end

  it "should allow several user to add the same cause to several organisations" do
    
  end

  it "should allow several user to add the same organisation to several causes" do
  end

  it "should give the number of (unique) user and organisation's name & id when querying by name" do
  end

  it "should give the most common organisation tagged with that cause" do
  end
end
