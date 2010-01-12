require 'spec_helper'

describe CauseOrganisation do
  before(:each) do
    @valid_attributes = {
      :cause_id => 1,
      :organisation_id => 1
    }
  end

  it "should create a new instance given valid attributes" do
    CauseOrganisation.create!(@valid_attributes)
  end

  it { should belong_to(:cause)}
  it { should belong_to(:organisation)}
end
