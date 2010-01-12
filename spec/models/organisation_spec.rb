require 'spec_helper'

describe Organisation do
  before(:each) do
    @valid_attributes = {
            :name => "musicbrainz",
            :paypal => "donations@musicbrainz.org",
            :url => "http://musicbrainz.org",
            :active => false
    }
    @organisation = Organisation.new(@valid_attributes)
  end

  it "should create a new organisation given valid attributes" do
    Organisation.create!(@valid_attributes)
  end

  it "should fail if no name is given" do
    @organisation.name = ''
    @organisation.should_not be_valid
  end

  it "should fail with no valid email" do
    @organisation.paypal = 'test'
    @organisation.should_not be_valid
  end

  after(:each) do
    # this will be run once after each example
    @organisation.destroy unless @organisation.new_record?
  end
end
