require 'spec_helper'

describe Cause do
  before(:each) do
    @user = User.make
    @organisation = Organisation.make

    @users = [
            user1 = User.make(:login => 'carl'),
            user2 = User.make(:login => 'kevin'),
            fairy = User.make(:login => 'Karina')
    ]

    @organisations = [
            Organisation.make(:name => 'peter pan'),
            Organisation.make(:name => 'amnesty' ),
            Organisation.make(:name=>'intergalactic')
    ]

  end

  after(:each) do

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

  it "should allow several user to add the same cause to the same organisation" do
    user1 = User.make
    user2 = User.make
    lambda {
      Cause.make(:name => "cause", :organisation => @organisation, :user => user1)
      Cause.make(:name => "cause", :organisation => @organisation, :user => user2)
    }.should change(Cause, :count).by(2)
  end

  it "should give the number of (unique) user and organisation's name & id when querying by name" do
    10.times{Cause.make(:name => "test")}
    causes_name = Cause.by_organisation.collect {|cause| cause.name}
    causes_name.should eql causes_name.sort
  end

  it "should give the most common organisation tagged with that cause" do
  end

  it "should order by number of user who tagged an organisation with that cause" do
    
  end
end
