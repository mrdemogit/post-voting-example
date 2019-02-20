import React from 'react'
import ShallowRenderer from 'react-test-renderer/shallow'
import { Post } from '../Post'

describe('Post', () => {
  it('should render correctly', () => {
    const renderer = new ShallowRenderer()
    renderer.render(
      <Post
        uuid='uuid1'
        author='authorTest'
        content='contentTest'
        votes={123}
        upvote={() => {}}
        downvote={() => {}}
        comments={[
          {
            uuid: 'uuid2',
            author: 'author2',
            content: 'contentTest2',
            votes: -331
          },
          {
            uuid: 'uuid3',
            author: 'author3',
            content: 'contentTest3',
            votes: 0
          }
        ]}
      />
    )

    expect(renderer.getRenderOutput()).toMatchSnapshot()
  })
})
