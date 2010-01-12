require 'spec_helper'

describe Organisation do
  it { should have_many(:causes)}
  it { should validate_presence_of (:causes)}
  it { should validate_presence_of (:paypal)}
  it { should validate_presence_of (:name)}
  it { should validate_presence_of (:url)}
  it { should allow_value("test@example.com").for(:paypal) }
  it { should_not allow_value("test").for(:paypal) }
  it { should allow_value("http://test.com").for(:url) }
  it { should_not allow_value("test.com").for(:url) }

  it "should only allow 1 same tag per organisation" do
    should_not allow_value(%w{test test}).for(:causes)
  end

  it "should allow up to 3 tags upon creation" do
  end

  it "should order by most number of tags" do
  end

  it "should find all organisations with specific tags" do
  end

  

end
