require 'spec_helper'

describe Organisation do
  it { should have_many(:causes)}
  it { should validate_presence_of(:paypal)}
  it { should validate_presence_of(:name)}
  it { should validate_presence_of(:url)}
  it { should allow_value("test@example.com").for(:paypal) }
  it { should_not allow_value("test").for(:paypal) }
  it { should allow_value("http://test.com").for(:url) }
  it { should_not allow_value("test.com").for(:url) }

  it "should allow up to no causes upon creation" do
  end

  it "should be able to update cause list with further causes" do
  end

  it "should order by most number of causes" do
  end

  it "should find all organisations with specific causes" do
  end

  describe "querying organisation" do
    before(:each) do
  #    @normal_organisation = Organisation.make
    end

    it "should fail for duplicate cause" do
#      @normal_organisation.causes << Cause.make(:name => "test")
#      lambda {@normal_organisation.save}.should raise
    end
  end

end